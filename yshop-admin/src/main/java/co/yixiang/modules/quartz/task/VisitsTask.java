package co.yixiang.modules.quartz.task;

import co.yixiang.modules.monitor.service.VisitsService;
import org.springframework.stereotype.Component;

/**
 * @author Zheng Jie
 * @date 2018-12-25
 */
@Component
public class VisitsTask {

    private final VisitsService visitsService;

    public VisitsTask(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    public void run() {
        visitsService.save();
    }
}
