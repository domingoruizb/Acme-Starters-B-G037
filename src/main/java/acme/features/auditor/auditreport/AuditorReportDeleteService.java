
package acme.features.auditor.auditreport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audits.AuditReport;
import acme.entities.audits.AuditSection;
import acme.realms.Auditor;

@Service
public class AuditorReportDeleteService extends AbstractService<Auditor, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorReportRepository	repository;

	private AuditReport				report;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.report = this.repository.findReportById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.report != null && this.report.getDraftMode() && this.report.getAuditor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.report, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		;
	}

	@Override
	public void execute() {
		Collection<AuditSection> sections;

		sections = this.repository.findSectionsByReportId(this.report.getId());
		this.repository.deleteAll(sections);
		this.repository.delete(this.report);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.report, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo", "draftMode", "monthsActive", "hours");
	}

}
