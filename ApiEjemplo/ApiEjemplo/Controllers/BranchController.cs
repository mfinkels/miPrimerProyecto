using ApiEjemplo.BranchInfo;
using ApiEjemplo.Data;
using ApiEjemplo.MenuInfo;
using ApiEjemplo.Models;
using ApiEjemplo.PromotionInfo;
using ApiEjemplo.RestaurantInfo;
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
        //[HttpGet]



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
      
        //anda
      

      

      

        // POST api/<controller>
        [Route("api/branch/menu")]
        [ResponseType(typeof(TypeMenu))]
        public IHttpActionResult Post(TypeMenu p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            TypeMenuData.update(p);
            return Ok(p);
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
        //anda
       

        [Route("api/branch/menu/{id}")]
        // PUT api/<controller>/5
        public IHttpActionResult Put(int id, TypeMenu menu)
        {
            if (id != menu.idBranchRestaurant)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del user es incorrecto.");
            }
            if (TypeMenuData.getById(id) == null)
            {
                return NotFound();
            }
            TypeMenuData.update(menu);
            return Ok(menu);
        }

        [Route("api/branch/menu/{id}")]
        // DELETE: api/<controller>/5
        public IHttpActionResult Deletemenues(int id)
        {
            if (TypeMenuData.getById(id) == null)
            {
                return NotFound();
            }
            TypeMenuData.Delete(id);
            return Ok();
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

       



        [Route("api/branch/TypeMenu/{id}")]
        public IHttpActionResult DeleteType(int id)
        {
            if (TypeMenuData.getById(id) == null)
            {
                return NotFound();
            }
            TypeMenuData.Delete(id);
            return Ok(id);
        }
       


       

       

      







                [Route("api/branch/TimetableBranch")]         public IList<TimetableBranch> TiemTable()         {             return TimetableBranchData.getAll();         }            

    }
}
