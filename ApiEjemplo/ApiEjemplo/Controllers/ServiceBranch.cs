using ApiEjemplo.Data;
using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using ApiEjemplo.BranchInfo;
using ApiEjemplo.MenuInfo;

namespace ApiEjemplo.Controllers
{
    public class ServiceBranchController : ApiController
    {

        [Route("api/branch/ServiceBranch")]
        public IList<ServiceBranch> ServiceBranch()
        {
            return ServiceBranchData.getAll();
        }
        [Route("api/branch/Service/{id}")]
        [ResponseType(typeof(Service))]
        public IHttpActionResult Getservice(int id)
        {
            Service branch = ServiceData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }

        //anda
        [Route("api/branch/Service/{id}")]
        [ResponseType(typeof(TimetableBranch))]
        public IHttpActionResult GetTimeTable(int id)
        {
            TimetableBranch branch = TimetableBranchData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }



        //anda
        [Route("api/branch/ServiceBranch/{id}")]
        [ResponseType(typeof(ServiceBranch))]
        public IHttpActionResult GetserviceBranch(int id)
        {
            ServiceBranch branch = ServiceBranchData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }

        //anda
        [Route("api/branch/Service")]
        public IList<Service> Getservice()
        {
            return ServiceData.getAll();
        }

        //seee anda

        [Route("api/branch/TypeMenu")]
        public IList<TypeMenu> GetTypeMenu()
        {
            return TypeMenuData.getAll();
        }

        // anda
        // POST api/<controller>
        [Route("api/branch/TypeMenu")]
        [ResponseType(typeof(TypeMenu))]
        public IHttpActionResult PostTypeMenu(TypeMenu p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            TypeMenuData.insert(p);

            return Ok(p);
        }







        //anda
        // POST api/<controller>
        [Route("api/branch/Service/{p}")]
        [ResponseType(typeof(Service))]
        public IHttpActionResult PostService(Service p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            ServiceData.update(p);
            return Ok(p);
        }


        //anda
        [Route("api/{id}/{limit}/{offset}")]
        public IHttpActionResult GetCalificationBranch(int id, int limit, int offset)
        {
            List<CalificationBranch> list = new List<CalificationBranch>();
            list = CalificationBranchData.getByBranch(id, limit, offset);
            if (list == null)
            {
                return NotFound();
            }
            return Ok(list);
        }

        //anda
        // GET api/<controller>/5 returns menu from branch
        [Route("api/branch/menu/{idBranch}")]
        public IList<TypeMenu> GetMenu(int idBranch)
        {
            return TypeMenuData.getMenuByBranch(idBranch);
        }
        //anda
        // GET api/<controller>/5 returns plates from menu
        [Route("api/branch/menu/{idTypeMenu}/plates")]
        public IList<PlateMenu> GetPlates(int idTypeMenu)
        {
            return MenuPlateData.getPlateMenuBranch(idTypeMenu);
        }
        //anda
        [Route("api/branch/TypeMenu/{idTypeMenu}")]
        public IHttpActionResult DeleteTypeMenu(int id)
        {
            if (TypeMenuData.getById(id) == null)
            {
                return NotFound();
            }
            TypeMenuData.Delete(id);
            return Ok();
        }

        // POST api/<controller>

        [ResponseType(typeof(BranchRestaurant))]
        public IHttpActionResult Post(BranchRestaurant branch)
        {
            if (branch == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            BranchRestaurantData.insert(branch);
            return Ok(branch);
        }

        //anda
        // POST api/<controller>
        [Route("api/branch/plate")]
        [ResponseType(typeof(PlateMenu))]
        public IHttpActionResult Post(PlateMenu p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            p.idPlateMenu = PlateMenuData.insert(p);

            return Ok(p);


        }

        // POST api/<controller>
        [Route("api/branch/PostServiceBranch")]
        [ResponseType(typeof(ServiceBranch))]
        public IHttpActionResult Post(ServiceBranch p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            ServiceBranchData.insert(p);

            return Ok(p);
        }

        // POST api/<controller>
        [Route("api/branch/ServiceBranch")]
        [ResponseType(typeof(ServiceBranch))]
        public IHttpActionResult Postservice(ServiceBranch p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            ServiceBranchData.update(p);
            return Ok(p);
        }
        [Route("api/branch/ServiceBranch/{id}")]
        // DELETE: api/<controller>/5
        public IHttpActionResult Deletemenu(int id)
        {
            if (ServiceBranchData.getById(id) == null)
            {
                return NotFound();
            }
            ServiceBranchData.Delete(id);
            return Ok();
        }
    }
}
