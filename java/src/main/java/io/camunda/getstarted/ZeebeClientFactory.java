package io.camunda.getstarted;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.BrokerInfo;
import io.camunda.zeebe.client.api.response.PartitionInfo;

import java.net.InetSocketAddress;
import java.util.List;

public class ZeebeClientFactory {

  public static ZeebeClient getZeebeClient() {

      final String addressString = "127.0.0.1:26500";
      ZeebeClient zeebeClient = ZeebeClient.newClientBuilder().gatewayAddress(addressString).usePlaintext().build();
      List<BrokerInfo> brokers = zeebeClient
              .newTopologyRequest()
              .send()
              .join()
              .getBrokers();

      int port = brokers.get(0).getPort();
      System.out.println(String.format("{0}: port",port));

      return zeebeClient;
      //    return ZeebeClient.newCloudClientBuilder()
//        .withClusterId("b9f6faf7-8e16-44f4-a0e6-13e6ad782f3e")
//        .withClientId("YA05jmADQrWAJf_2a-wGgQmzx8qkbUVX")
//        .withClientSecret("TNtav2qM~0q9VadJP9Vzim8wFHEWFJA6a~xdTCReiuh0ZWrg1bKXGBsLG63__NvK")
//        .withRegion("syd-1")
//        .build();
  }

}
