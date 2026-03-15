
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.MomentHelper;
import acme.entities.audits.AuditReport;
import acme.entities.audits.AuditRepository;

public class ReportValidator extends AbstractValidator<ValidReport, AuditReport> {

	@Autowired
	private AuditRepository repository;


	@Override
	protected void initialise(final ValidReport annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final AuditReport report, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		{
			boolean uniqueTicker;
			AuditReport existingReport;

			existingReport = this.repository.findReportByTicker(report.getTicker());
			uniqueTicker = existingReport == null || existingReport.equals(report);

			super.state(context, uniqueTicker, "ticker", "acme.validation.audit-report.uniqueticker.message");
		}
		if (report.getDraftMode().equals(Boolean.FALSE)) {
			{
				boolean validInterval;

				if (report.getStartMoment() != null && report.getEndMoment() != null)
					validInterval = MomentHelper.isAfterOrEqual(report.getEndMoment(), report.getStartMoment());
				else
					validInterval = false;

				super.state(context, validInterval, "*", "acme.validation.audit-report.invalidinterval.message");
			}
			{
				boolean hasSections;
				hasSections = this.repository.existsByReportId(report.getId());

				super.state(context, hasSections, "*", "acme.validation.audit-report.hassection.message");
			}
		}

		result = !super.hasErrors(context);

		return result;
	}
}
