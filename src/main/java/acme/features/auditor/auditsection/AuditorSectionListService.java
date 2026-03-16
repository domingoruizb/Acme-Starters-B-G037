
package acme.features.auditor.auditsection;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audits.AuditReport;
import acme.entities.audits.AuditSection;
import acme.realms.Auditor;

@Service
public class AuditorSectionListService extends AbstractService<Auditor, AuditSection> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorSectionRepository	repository;

	private AuditReport					report;
	private Collection<AuditSection>	sections;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int reportId;

		reportId = super.getRequest().getData("audit-reportId", int.class);
		this.report = this.repository.findReportById(reportId);
		this.sections = this.repository.findSectionsByReportId(reportId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.report != null && (!this.report.getDraftMode() || this.report.getAuditor().isPrincipal());

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		boolean showCreate;

		super.unbindObjects(this.sections, "name", "notes", "hours", "kind");

		showCreate = this.report.getDraftMode() && this.report.getAuditor().isPrincipal();
		super.unbindGlobal("auditReportId", this.report.getId());
		super.unbindGlobal("showCreate", showCreate);
	}

}
