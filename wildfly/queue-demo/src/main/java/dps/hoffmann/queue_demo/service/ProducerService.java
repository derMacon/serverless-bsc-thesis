package dps.hoffmann.queue_demo.service;

import dps.hoffmann.queue_demo.jms.Sender;
import dps.hoffmann.queue_demo.model.Employee;
import dps.hoffmann.queue_demo.utils.EmployeeUtils;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

@ApplicationScoped
@Slf4j
public class ProducerService {

    @Inject
    private Sender sender;

    public boolean sendTextMessage(String content) {
        return sender.createTask(content);
    }

    public boolean sendEmployee(Employee e) {
        return sender.createTask(e);
    }

    public boolean sendXmlMessage(InputStream xmlInputStream) {
        Employee employee = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            employee = (Employee)jaxbUnmarshaller.unmarshal(xmlInputStream);
            log.info("empl: {}", employee);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return sendEmployee(employee);
    }

    public boolean sendSampleXml() {
        return sendEmployee(EmployeeUtils.createSampleEmployee());
    }
}
