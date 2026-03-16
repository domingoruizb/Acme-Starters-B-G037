
package acme.features.auditor.auditsection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.audits.AuditSection;
import acme.realms.Auditor;

@Service
public class AuditorSectionDeleteService extends AbstractService<Auditor, AuditSection> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorSectionRepository	repository;

	private AuditSection				section;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.section = this.repository.findSectionById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.section != null && //
			this.section.getAuditReport().getDraftMode() && this.section.getAuditReport().getAuditor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.section, "name", "notes", "hours", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.section);
	}

	@Override
	public void execute() {
		this.repository.delete(this.section);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.section, "name", "notes", "hours", "kind");
		tuple.put("auditReportId", this.section.getAuditReport().getId());
		tuple.put("draftMode", this.section.getAuditReport().getDraftMode());
	}
}
