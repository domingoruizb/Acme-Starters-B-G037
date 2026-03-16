
package acme.features.auditor.auditreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.audits.AuditReport;
import acme.realms.Auditor;

@Service
public class AuditorReportPublishService extends AbstractService<Auditor, AuditReport> {

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
		super.validateObject(this.report);

		{
			boolean uniqueTicker;
			AuditReport existingReport;

			existingReport = this.repository.findReportByTicker(this.report.getTicker());
			uniqueTicker = existingReport == null || existingReport.equals(this.report);

			super.state(uniqueTicker, "ticker", "acme.validation.audit-report.uniqueticker.message");
		}
		{
			boolean startMomentInFuture;

			startMomentInFuture = MomentHelper.isFuture(this.report.getStartMoment());
			super.state(startMomentInFuture, "startMoment", "acme.validation.audit-report.startmomentinfuture.message");
		}
		{
			boolean endMomentInFuture;

			endMomentInFuture = MomentHelper.isFuture(this.report.getEndMoment());
			super.state(endMomentInFuture, "endMoment", "acme.validation.audit-report.endmomentinfuture.message");
		}
		{
			boolean validInterval;

			if (this.report.getStartMoment() != null && this.report.getEndMoment() != null)
				validInterval = MomentHelper.isAfterOrEqual(this.report.getEndMoment(), this.report.getStartMoment());
			else
				validInterval = false;

			super.state(validInterval, "*", "acme.validation.audit-report.invalidinterval.message");
		}
		{
			boolean hasSections;
			hasSections = this.repository.existsByReportId(this.report.getId());

			super.state(hasSections, "*", "acme.validation.audit-report.hassections.message");
		}
	}

	@Override
	public void execute() {
		this.report.setDraftMode(false);
		this.repository.save(this.report);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.report, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo", "draftMode", "monthsActive", "hours");
	}
}
