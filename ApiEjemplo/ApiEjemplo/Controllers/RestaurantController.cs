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
    public class RestaurantController : ApiController
    {

        // GET api/<controller>
        public IList<Restaurant> Get()
        {
            return RestaurantData.getAll();
        }

        // GET api/<controller>/5
        [ResponseType(typeof(Restaurant))]
        public IHttpActionResult Get(int id)
        {
            Restaurant resto = RestaurantData.getById(id);
            if (resto == null)
            {
                return NotFound();
            }
            return Ok(resto);
        }


        // POST api/<controller>
        [ResponseType(typeof(Restaurant))]
        public IHttpActionResult Post(Restaurant resto)
        {
            if (resto == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            RestaurantData.Insert(resto);
            return Ok(resto);
        }

        // PUT api/<controller>/5
        public IHttpActionResult Put(int id, Restaurant resto)
        {
            if (id != resto.idRestaurant)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del user es incorrecto.");
            }
            if (RestaurantData.getById(id) == null)
            {
                return NotFound();
            }
            RestaurantData.Update(resto);
            return Ok(resto);
        }

        // DELETE: api/<controller>/5
        public IHttpActionResult Delete(int id)
        {
            if (RestaurantData.getById(id) == null)
            {
                return NotFound();
            }
            RestaurantData.Delete(id);
            return Ok();
        }
    }
}
