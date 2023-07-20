package org.example.utils;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import org.testng.util.Strings;

import java.util.Calendar;
import java.util.function.Supplier;

public class ParamOverrideTestNgService extends TestNGService {

    public ParamOverrideTestNgService() {
        super(getLaunchOverriddenProperties());
    }

    private static Supplier getLaunchOverriddenProperties() {
        ListenerParameters parameters = new ListenerParameters(PropertiesLoader.load());
        parameters.setApiKey("jo2bYh2-sHWQ9HuTQcYzTMbxqO8");
        ReportPortal reportPortal = ReportPortal.builder().withParameters(parameters).build();
        StartLaunchRQ rq = buildStartLaunch(reportPortal.getParameters());
        return (Supplier<Launch>) () -> reportPortal.newLaunch(rq);
    }

    private static StartLaunchRQ buildStartLaunch(ListenerParameters parameters) {
        StartLaunchRQ rq = new StartLaunchRQ();
        rq.setName(parameters.getLaunchName());
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setAttributes(parameters.getAttributes());
        rq.setMode(parameters.getLaunchRunningMode());
        if (!Strings.isNullOrEmpty(parameters.getDescription())) {
            rq.setDescription(parameters.getDescription());
        }
        return rq;
    }
}
