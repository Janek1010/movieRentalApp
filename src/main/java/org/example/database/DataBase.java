package org.example.database;

import org.example.Util.CloningUtility;
import org.example.user.entity.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataBase {
    private final Set<User> users = new HashSet<>();

    private final CloningUtility cloningUtility;

    public DataBase(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
        users.add(User.builder().id(UUID.randomUUID()).email("example@org").username("jurek").registrationDate(LocalDate.now()).build());
        users.add(User.builder().id(UUID.randomUUID()).email("example@com").username("marek").registrationDate(LocalDate.now()).build());
        users.add(User.builder().id(UUID.randomUUID()).email("plexa@moc").username("jarek").registrationDate(LocalDate.now()).build());
        users.add(User.builder().id(UUID.randomUUID()).email("klexa@gor").username("mirek").registrationDate(LocalDate.now()).build());
    }

    public synchronized List<User> findAllUsers(){
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }
    public synchronized User findUserById(UUID uuid){
        return users.stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst()
                .map(cloningUtility::clone)
                .orElse(null);
    }
}
