/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package session;

import fortress.lighthouse.session.service.v1.DeviceId;
import fortress.lighthouse.session.service.v1.GetSessionRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class ApplicationTests {

	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	@Value("${local.server.port}")
	private int port = 0;

	@Before
	public void init() throws Exception {
		marshaller.setPackagesToScan(ClassUtils.getPackageName(GetSessionRequest.class));
		marshaller.afterPropertiesSet();
	}
	@Test
	public void testSendAndReceive() throws InterruptedException {
		GetSessionRequest request = new GetSessionRequest();
		DeviceId device = new DeviceId();
		device.setImei("testImei");
		device.setAndroidId("testAndroidId");
		device.setCdma("testCdma");
		request.setDeviceId(device);
		Thread.sleep(200000);
		assertNotNull(new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:"
				+ port + "/ws", request));
	}

}
