/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageSlotImp;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author orcl
 */
@Path("/slotStatus")
    
public class UpdateSlotStatus {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String updateSlot(@QueryParam("slotid") int slotid, @QueryParam("garageid") int garageid , @QueryParam("status") int status) {
        return GarageSlotImp.getInstance().UpdateGarageSlot(slotid, garageid , status);
    }

}
