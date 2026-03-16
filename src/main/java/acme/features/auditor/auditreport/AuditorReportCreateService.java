
package acme.features.auditor.auditreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audits.AuditReport;
import acme.realms.Auditor;

@Service
public class AuditorReportCreateService extends AbstractService<Auditor, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorReportRepository	repository;

	private AuditReport				report;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		Auditor auditor;

		auditor = (Auditor) super.getRequest().getPrincipal().getActiveRealm();

		this.report = super.newObject(AuditReport.class);
		this.report.setDraftMode(true);
		this.report.setAuditor(auditor);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void bind() {
		super.bindObject(this.report, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.report);
	}

	@Override
	public void execute() {
		this.repository.save(this.report);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.report, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo", "draftMode", "monthsActive", "hours");
	}

}
