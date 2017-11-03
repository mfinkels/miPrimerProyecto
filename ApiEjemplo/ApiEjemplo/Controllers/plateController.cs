using ApiEjemplo.Data;
using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ApiEjemplo.Controllers
{
    public class plateController : ApiController
    {

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
        // POST api/<controller>
        [Route("api/branch/MenuPlate")]
        [ResponseType(typeof(MenuPlate))]
        public IHttpActionResult Post(MenuPlate p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            MenuPlateData.insert(p);

            return Ok(p);

        }
    }
}
