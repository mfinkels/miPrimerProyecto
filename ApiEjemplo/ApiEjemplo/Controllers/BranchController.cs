using ApiEjemplo.BranchInfo;
using ApiEjemplo.Data;
using ApiEjemplo.MenuInfo;
using ApiEjemplo.Models;
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
        // GET api/<controller>/CategoryPlate
        [Route("api/branch/CategoryPlate")]
        public IList<CategoryPlate> GetCategory()
        {
            return CategoryPlateData.getAll();
        }
        

        [ResponseType(typeof(CategoryPlate))]
          [Route("api/branch/CategoryPlate/{id}")]//idCategoryPlate
        public IHttpActionResult GetCategory(int id)
        {
            CategoryPlate category = CategoryPlateData.getById(id);
            if (category == null)
            {
                return NotFound();
            }
            return Ok(category);
        }
       
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

        // POST api/<controller>
        [Route("api/branch/plate")]
        [ResponseType(typeof(PlateMenu))]
        public IHttpActionResult Post(PlateMenu p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            PlateMenuData.insert(p);
           
          return Ok(p);
      
        }
        
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

        [Route("api/branch/category/{id}")]
        public IHttpActionResult PutCategory(int id, CategoryPlate categoryplate)
        {
            if (id != categoryplate.idCategoryPlate)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del user es incorrecto.");
            }
            if (CategoryPlateData.getById(id) == null)
            {
                return NotFound();
            }
            CategoryPlateData.update(categoryplate);
            return Ok(categoryplate);
        }
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
        public IHttpActionResult Deletemenu(int id)
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

        // PUT api/<controller>/5
        [Route("api/branch/plate/{id}")]
        [HttpPut]
        public IHttpActionResult PutPlate(int id, PlateMenu plate)
        {
            if (id != plate.idPlateMenu)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del plate es incorrecto.");
            }
            if (PlateMenuData.getById(plate.idPlateMenu) == null)
            {
                return NotFound();
            }
            PlateMenuData.update(plate);
            return Ok(plate);
        }


        [Route("api/branch/plate/{id}")]
        public IHttpActionResult DeletePlate(int id)
        {
            if (PlateMenuData.getById(id) == null)
            {
                return NotFound();
            }
            PlateMenuData.delete(id);
            return Ok();
        }

    }
}
