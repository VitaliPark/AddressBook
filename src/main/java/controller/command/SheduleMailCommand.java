package controller.command;

import org.apache.log4j.Logger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import controller.MailSendJob;
import model.service.ContactService;
import model.service.MailService;

public class SheduleMailCommand implements Command{

	private MailService mailService;
	private ContactService contactService;
	private Logger LOGGER = Logger.getLogger(SheduleMailCommand.class);
	
	public SheduleMailCommand(MailService mailService,
			ContactService contactService) {
		super();
		this.mailService = mailService;
		this.contactService = contactService;
	}

	@Override
	public void execute() {
		scheduleTask();
	}

	@Override
	public String getResultPage() {
		return "";
	}
	
	private void scheduleTask(){
		System.setProperty("org.quartz.scheduler.makeSchedulerThreadDaemon", 
				"true");
				System.setProperty("org.quartz.threadPool.makeThreadsDaemons", "true");
		Trigger trigger =  initTrigger();
		
	    JobDetail job = initJob();
		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("Scheduler error:" + e.getMessage());
		}
	}

	private JobDetail initJob() {
		JobDetail job = JobBuilder.newJob(MailSendJob.class)
				.withIdentity("dummyJobName", "group1").build();
	    job.getJobDataMap().put("contactService", contactService);
	    job.getJobDataMap().put("mailService", mailService);
		return job;
	}

	private Trigger initTrigger() {
		Trigger trigger = TriggerBuilder
	    		.newTrigger()
	    		.withIdentity("dummyTriggerName", "group1")
	    		.startAt(DateBuilder.todayAt(21, 39, 0))
	    	    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	    	            .withIntervalInHours(24)
	    	            .repeatForever())
	    		.build();
		return trigger;
	}

	
}
