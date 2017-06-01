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
    public class UserController : ApiController
    {
        // GET api/<controller>
        public IList<User> Get()
        {
            return UserData.getAll();
        }

        // GET api/<controller>/5
        [ResponseType(typeof(User))]
        public IHttpActionResult Get(int id)
        {
            User user = UserData.getById(id);
            if (user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }

        [Route("api/user/{email}/{password}")]
        public IHttpActionResult Get(string email, string password)
        {
            User user = new User();
            user = UserData.getUser(email, password);
            if (user == null) {
                return BadRequest("Email o contrasena incorrecta.");
            }
            return Ok(user);
        }


        // POST api/<controller>
        [ResponseType(typeof(User))]
        public IHttpActionResult Post(User user)
        {
            if (user == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            UserData.insert(user);
            return Ok();
        }

        // PUT api/<controller>/5
        public IHttpActionResult Put(int id, User user)
        {
            if (id != user.idUser)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del user es incorrecto.");
            }
            if (UserData.getById(id) == null)
            {
                return NotFound();
            }
            UserData.Update(user);
            return Ok();
        }
    }
}