package app.service;

import app.entities.User;
import app.entities.UserType;
import app.repositories.UserRepository;
import app.repositories.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserTypeRepository userTypeRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {return userRepository.findById(id).orElse(null);}

    public User login(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    @Transactional
    public ResponseEntity<?> register(User request)  {
        String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        if(!request.getEmail().matches(emailRegex))
            return ResponseEntity.badRequest().body("Invalid Email!");

        String usernameRegex = "^[a-zA-Z0-9_-]{5,30}$";
        if(!request.getUsername().matches(usernameRegex))
            return ResponseEntity.badRequest().body("Invalid username length!");

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+])[0-9a-zA-Z!@#$%^&*()_+]{8,}$";
        if(!request.getPassword().matches(regex)) {
            return ResponseEntity.badRequest().body("Invalid password format!");
        }

        if(userRepository.findByUsername(request.getUsername())!=null)
            return ResponseEntity.badRequest().body("Username already exists!");


        if(userRepository.findByEmail(request.getEmail())!=null) {
            return ResponseEntity.badRequest().body("Email already exists!");
        }

        UserType userType = userTypeRepository.findByType("user");

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .userType(userType)
                .avatar(null)
                .locked(false)
                .enabled(false)
                .city(request.getCity())
                .build();


        userRepository.save(user);

        String confirmationLink = null;
        confirmationLink = "http://localhost:8080/email/register/confirm/" + user.getId();
        emailService.send(request.getEmail(), buildEmail(request.getUsername(), confirmationLink));
        return ResponseEntity.ok().build();

    }

    @Transactional
    public ResponseEntity<?> edit(User request)  {
        String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        if(!request.getEmail().matches(emailRegex))
            return ResponseEntity.badRequest().body("Invalid Email!");

        String usernameRegex = "^[a-zA-Z0-9_-]{5,30}$";
        if(!request.getUsername().matches(usernameRegex))
            return ResponseEntity.badRequest().body("Invalid username length!");

        if(request.getPassword()!=null) {
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+])[0-9a-zA-Z!@#$%^&*()_+]{8,}$";
            if (!request.getPassword().matches(regex)) {
                return ResponseEntity.badRequest().body("Invalid password format!");
            }
        }

        if(userRepository.findByEmail(request.getEmail())!=null) {
            return ResponseEntity.badRequest().body("Email already exists!");
        }

        User existingUser = userRepository.findByUsername(request.getUsername());

        existingUser.setCity(request.getCity());
        existingUser.setEmail(request.getEmail());
        existingUser.setFirstname(request.getFirstname());
        existingUser.setLastname(request.getLastname());
        if(request.getPassword()!=null)
             existingUser.setPassword(request.getPassword());

        userRepository.save(existingUser);

        return ResponseEntity.ok().build();

    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family: Helvetica, Arial, sans-serif; font-size: 16px; margin: 0; color: #1a1a1a;\">\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse: collapse; min-width: 100%; width: 100% !important;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td width=\"100%\" height=\"53\" bgcolor=\"#3498db\">\n" +
                "          <table role=\"presentation\" width=\"100%\" style=\"border-collapse: collapse; max-width: 580px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "            <tr>\n" +
                "              <td width=\"70\" bgcolor=\"#3498db\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse;\">\n" +
                "                  <tr>\n" +
                "                    <td style=\"padding-left: 10px;\">\n" +
                "                    </td>\n" +
                "                    <td style=\"font-size: 28px; line-height: 1.315789474; Margin-top: 4px; padding-left: 10px;\">\n" +
                "                      <span style=\"font-family: Helvetica, Arial, sans-serif; font-weight: 700; color: #ffffff; text-decoration: none; vertical-align: top; display: inline-block;\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse; max-width: 580px; width: 100% !important;\" width=\"100%\">\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "        <td>\n" +
                "          <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse;\">\n" +
                "            <tr>\n" +
                "              <td bgcolor=\"#fbd1a2\" width=\"100%\" height=\"10\"></td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "        <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse: collapse; max-width: 580px; width: 100% !important;\" width=\"100%\">\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td height=\"30\"><br></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "        <td style=\"font-family: Helvetica, Arial, sans-serif; font-size: 19px; line-height: 1.315789474; max-width: 560px;\">\n" +
                "          <p style=\"margin: 0 0 20px 0; font-size: 19px; line-height: 25px; color: #1a1a1a;\">Zdravo " + name + ",</p>\n" +
                "          <p style=\"margin: 0 0 20px 0; font-size: 19px; line-height: 25px; color: #1a1a1a;\">Hvala Vam na registraciji. Molimo Vas da kliknete na link ispod da biste aktivirali Vaš nalog:</p>\n" +
                "          <blockquote style=\"margin: 0 0 20px 0; border-left: 10px solid #fbd1a2; padding: 15px 0 0.1px 15px; font-size: 19px; line-height: 25px;\">\n" +
                "            <p style=\"margin: 0 0 20px 0; font-size: 19px; line-height: 25px; color: #1a1a1a;\">\n" +
                "              <a href=\"" + link + "\">Aktivirajte odmah</a>\n" +
                "            </p>\n" +
                "          </blockquote>\n" +
                "          Link će biti aktivan u narednih 15 minuta.\n" +
                "          <p>Hvala Vam na povjerenju,</p>\n" +
                "          <p>FitnessApp HelpDesk</p>\n" +
                "        </td>\n" +
                "        <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td height=\"30\"><br></td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  <div class=\"yj6qo\"></div>\n" +
                "  <div class=\"adL\"></div>\n" +
                "</div>";
    }

    public void sendConfirmationMail(String username) {
        User user = userRepository.findByUsername(username);
        emailService.send(user.getEmail(), buildEmail(user.getUsername(), "http://localhost:8080/email/register/confirm/" + user.getId()));
    }


    public User confirmId(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));

        user.setEnabled(true);
        return userRepository.save(user);
    }
}
