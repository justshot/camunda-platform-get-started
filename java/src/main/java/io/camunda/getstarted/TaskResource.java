package io.camunda.getstarted;

import io.camunda.zeebe.client.ZeebeClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskResource {
    private static final Logger LOG = LogManager.getLogger(TaskResource.class);

    public static void main(String[] args) {
        try (ZeebeClient client = ZeebeClientFactory.getZeebeClient()) {
            long key = 2251799813686060L;
            client.newCompleteCommand(key).send().join();
            LOG.info("Task completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
