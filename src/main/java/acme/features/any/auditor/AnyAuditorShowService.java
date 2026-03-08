
package acme.features.any.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.audits.AuditReport;
import acme.realms.Auditor;

@Service
public class AnyAuditorShowService extends AbstractService<Any, Auditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyAuditorRepository	repository;

	private Auditor					auditor;
	private AuditReport				auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int reportId;

		reportId = super.getRequest().getData("auditReportId", int.class);
		this.auditReport = this.repository.findAuditReportById(reportId);
		this.auditor = this.repository.findAuditorByReportId(reportId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.auditor != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditor, "identity.fullName", "firm", "highlights", "solicitor");
	}

}
