package com.nowcoder.async;

import java.util.List;

public interface EventHandler {
    void doHandle(EventModel model);
    // 存放 关注的 EventType
    List<EventType> getSupportEventTypes();

}
