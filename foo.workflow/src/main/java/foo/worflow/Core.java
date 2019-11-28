package foo.worflow;

import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.runtime.TaskRuntime;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
class Core implements CommandLineRunner {

    private final TaskRuntime taskRuntime;

    private final SecurityUtil securityUtil;

    public Core(TaskRuntime taskRuntime, SecurityUtil securityUtil) {
        this.taskRuntime = taskRuntime;
        this.securityUtil = securityUtil;
    }

    public static void main(String[] args) {
        SpringApplication.run(Core.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // 组内人登录 创建任务
        securityUtil.logInAs("svvx");

        System.out.println(("组内人 svvx 创建一个属组任务"));
        taskRuntime.create(TaskPayloadBuilder.create()
                .withName("属组任务")
                .withDescription("我属于 GROUP_INNER")
                .withCandidateGroup("INNER")
                .withPriority(5)
                .build());

        // 组外人 登录
        securityUtil.logInAs("dxh");
        System.out.println("组外人 dxh 在获取任务");
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        System.out.println("组外人 dxh 获取到任务: " + tasks.getTotalItems());


        securityUtil.logInAs("qqlove");
        System.out.println("组内人 qqlove 在获取任务");
        tasks = taskRuntime.tasks(Pageable.of(0, 10));
        String availableTaskId = tasks.getContent().get(0).getId();
        System.out.println("组内人 qqlove 获取到任务: " + tasks.getTotalItems());

        System.out.println("组内人 qqlove 完成任务中");
        taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(availableTaskId).build());

        System.out.println("组内人 qqlove 已经完成任务");
        taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(availableTaskId).build());
    }




}
