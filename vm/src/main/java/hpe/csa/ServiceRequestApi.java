package hpe.csa;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hpe.csa.model.ServiceRequest;
import hpe.csa.model.VM;
import hpe.csa.serviceUtil.SubscriptionManagerImpl;

@Path("request")
public class ServiceRequestApi {

	@POST
	@Path("vm")
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response requestVM(ServiceRequest request) {
		String res="";
		VM responseVm=new VM();
		SubscriptionManagerImpl serviceRequestSvc=new SubscriptionManagerImpl();
		try {
			VM resultVm=serviceRequestSvc.requestVM(request);
			//VM responseVm=new VM(resultVm.getHostname(),resultVm.getVersion(),resultVm.getVmUser(),resultVm.getPassword(),resultVm.isInUse());
			responseVm.setId(resultVm.getId());
			responseVm.setHostname(resultVm.getHostname());
			responseVm.setVersion(resultVm.getVersion());
			responseVm.setVmUser(resultVm.getVmUser());
			responseVm.setPassword(resultVm.getPassword());
			responseVm.setInUse(resultVm.isInUse());
			System.out.println("Response VM:");
			responseVm.printVM();
			
		}catch (RuntimeException re) {
			res+=re.getMessage();
			System.out.println("Exception"+res);
		}catch (Exception e) {
			res+=e.getMessage();
		}
		if(res.length()>0) {
			return Response.ok().entity(res).build();
		}
		
		
		return Response.ok().entity(responseVm).build();
	}
	
}
