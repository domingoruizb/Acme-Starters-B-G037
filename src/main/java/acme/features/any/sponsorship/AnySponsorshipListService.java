
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsors.Sponsorship;

@Service
public class AnySponsorshipListService extends AbstractService<Any, Sponsorship> {

	@Autowired
	private AnySponsorshipRepository	repository;

	private Collection<Sponsorship>		sponsorships;


	@Override
	public void load() {
		this.sponsorships = this.repository.findAllSponsorships();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "description", "startMoment", "endMoment", "sponsor.identity.fullName");
	}
}
