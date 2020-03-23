package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;

@AllArgsConstructor
@Service
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public void createVerificationToken(AppUser appUser, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setAppUser(appUser);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token);
    }

    public void removeTokenFromDataBase(VerificationToken token) {
        verificationTokenRepository.delete(token);
    }
}
