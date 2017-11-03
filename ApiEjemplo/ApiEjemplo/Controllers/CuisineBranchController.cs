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

namespace ApiEjemplo.Controllers
{
    public class CuisineBranchController : ApiController
    {
        //anda
        [Route("api/branch/Cuisine/{id}")]
        [ResponseType(typeof(Cuisine))]
        public IHttpActionResult Cuisine(int id)
        {
            Cuisine branch = CuisineData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }
        //anda
        [Route("api/branch/Cuisine")]
        public IList<Cuisine> GetCuisine()
        {
            return CuisineData.getAll();
        }
    }
}
