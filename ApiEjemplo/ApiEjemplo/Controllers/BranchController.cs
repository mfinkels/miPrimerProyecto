using ApiEjemplo.BranchInfo;
using ApiEjemplo.Data;
using ApiEjemplo.MenuInfo;
using ApiEjemplo.PromotionInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ApiEjemplo.Controllers
{
    public class BranchController : ApiController
    {
        // GET api/<controller>
        [Route("api/branch/{limit}/{offset}")]
        public IList<BranchRestaurant> Get(int limit, int offset)
        {
            return BranchRestaurantData.getAll(limit, offset);
        }
        // GET api/<controller>/5
        [ResponseType(typeof(BranchRestaurant))]
        public IHttpActionResult Get(int id)
        {
            BranchRestaurant branch = BranchRestaurantData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }

        // GET api/<controller>/5 returns menu from branch
        [Route("api/branch/menu/{idBranch}")]
        public IList<TypeMenu> GetMenu(int idBranch)
        {
            return TypeMenuData.getMenuByBranch(idBranch);
        }

        // GET api/<controller>/5 returns plates from menu
        [Route("api/branch/menu/{idTypeMenu}/plates")]
        public IList<PlateMenu> GetPlates(int idTypeMenu)
        {
            return MenuPlateData.getPlateMenuBranch(idTypeMenu);
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

        // PUT api/<controller>/5
        public IHttpActionResult Put(int id, BranchRestaurant branch)
        {
            if (id != branch.idBranchRestaurant)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del user es incorrecto.");
            }
            if (BranchRestaurantData.getById(id) == null)
            {
                return NotFound();
            }
            BranchRestaurantData.update(branch);
            return Ok(branch);
        }

        // DELETE: api/<controller>/5
        public IHttpActionResult Delete(int id)
        {
            if (BranchRestaurantData.getById(id) == null)
            {
                return NotFound();
            }
            BranchRestaurantData.delete(id);
            return Ok();
        }
    }
}
