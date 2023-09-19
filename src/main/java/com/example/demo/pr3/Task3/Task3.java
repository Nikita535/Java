package com.example.demo.pr3.Task3;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Task3 {
    private static final Random random = new Random();
    private static final List<UserFriend> userFriends = List.of(
            new UserFriend(1, 2),
            new UserFriend(2, 1),
            new UserFriend(1, 3),
            new UserFriend(3, 1),
            new UserFriend(4, 2),
            new UserFriend(2, 4),
            new UserFriend(5, 3),
            new UserFriend(3, 5),
            new UserFriend(6, 5),
            new UserFriend(5, 6)
    );

    public static void main(String[] args) {
        Observable.fromIterable(generateUserIds())
                .flatMap(Task3::getFriends)
                .subscribe(System.out::println);
    }

    private static Observable<UserFriend> getFriends(int userId){
        ArrayList<UserFriend> friends = userFriends.stream()
                .filter(userFriend -> userFriend.getUserId() == userId)
                .collect(Collectors.toCollection(ArrayList::new));

        return Observable.fromIterable(friends);
    }

    private static List<Integer> generateUserIds(){
        List<Integer> userIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
             userIds.add( random.nextInt(7) + 1);
        }
        return userIds;
    }
}
