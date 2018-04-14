package com.nowcoder.model;

import com.nowcoder.model.User;
import org.springframework.stereotype.Component;

/**
 * 当前用户是谁
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
