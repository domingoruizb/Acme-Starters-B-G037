
package acme.features.any.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Service
public class AnyInventorShowService extends AbstractService<Any, Inventor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyInventorRepository	repository;

	private Inventor				inventor;
	private Invention				invention;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int inventionId;

		inventionId = super.getRequest().getData("inventionId", int.class);
		this.invention = this.repository.findInventionById(inventionId);
		this.inventor = this.repository.findInventorByInventionId(inventionId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.inventor != null && this.invention != null && !this.invention.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "identity.fullName", "bio", "key-words", "licensed");
	}

}
