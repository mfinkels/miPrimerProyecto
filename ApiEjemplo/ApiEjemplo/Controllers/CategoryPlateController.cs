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
    public class CategoryPlateController : ApiController
    {

        //anda
        // GET api/<controller>/CategoryPlate
        [Route("api/branch/CategoryPlate")]
        public IList<CategoryPlate> GetCategory()
        {
            return CategoryPlateData.getAll();
        }
        //anda

        //anda
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
        // POST api/<controller>
        [Route("api/branch/CategoryPlate")]
        [ResponseType(typeof(CategoryPlate))]
        public IHttpActionResult Post(CategoryPlate p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            CategoryPlateData.insert(p);

            return Ok(p);
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
        [Route("api/branch/CategoryPlate/{id}")]
        public IHttpActionResult DeleteCategory(int id)
        {
            if (CategoryPlateData.getById(id) == null)
            {
                return NotFound();
            }
            CategoryPlateData.delete(id);
            return Ok(id);
        }

       


    }
}
