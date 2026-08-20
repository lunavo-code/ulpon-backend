package org.dromara.ai.service;

public interface IAiAgentService {
    void info(Long agentId);
    void list();
    void create();
    void update();
    void delete();
}
