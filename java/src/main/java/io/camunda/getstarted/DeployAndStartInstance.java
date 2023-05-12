package io.camunda.getstarted;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeployAndStartInstance {

  private static final Logger LOG = LogManager.getLogger(DeployAndStartInstance.class);

  public static void main(String[] args) {
    try (ZeebeClient client = ZeebeClientFactory.getZeebeClient()) {
      client.newDeployResourceCommand()
          .addResourceFromClasspath("simple-user-task.bpmn")
          .send()
          .join();

      final ProcessInstanceEvent event = client.newCreateInstanceCommand()
          .bpmnProcessId("Process_6ada859e-b057-4e4c-b2e4-69c22dcb3c01")
          .latestVersion()
          .variables(Map.of("message_content", "Hello from the Java get started"))
          .send()
          .join();

      LOG.info(
          "Started instance for processDefinitionKey='{}', bpmnProcessId='{}', version='{}' with processInstanceKey='{}'",
          event.getProcessDefinitionKey(), event.getBpmnProcessId(), event.getVersion(),
          event.getProcessInstanceKey());
    }
  }
}