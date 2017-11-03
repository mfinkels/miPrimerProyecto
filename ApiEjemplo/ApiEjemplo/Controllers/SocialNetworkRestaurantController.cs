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
    public class SocialNetworkRestaurantController : ApiController
    {

        [Route("api/branch/SocialNetwork")]
        public IList<SocialNetworkRestaurant> GetSocial()
        {
            return SocialNetworkRestaurantData.getAll();
        }
         [Route("api/branch/TypeSocialNetwork/{id}")]
        [ResponseType(typeof(TypeSocialNetwork))]
        public IHttpActionResult GetTypeSocialNetwork(int id)
        {
            TypeSocialNetwork branch = TypeSocialNetworkData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }
        //anda
        [Route("api/branch/DeleteSocialNetwork/{id}")]
        public IHttpActionResult DeleteSocialNetwork(int id)
        {
            if (SocialNetworkRestaurantData.getById(id) == null)
            {
                return NotFound();
            }
            SocialNetworkRestaurantData.delete(id);
            return Ok();
        }

        [ResponseType(typeof(SocialNetworkRestaurant))]
        public IHttpActionResult PostInsertSocial(SocialNetworkRestaurant social)
        {
            if (social == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            SocialNetworkRestaurantData.insert(social);
            return Ok(social);
        }
        //no anda y no se pruqe si son todos los metodos iguales
        [ResponseType(typeof(SocialNetworkRestaurant))]
        [Route("api/branch/SocialNetworkRestaurant/{id}")]
        public IHttpActionResult GetSocialNetworkRestaurant(int id)
        {
            SocialNetworkRestaurant category = SocialNetworkRestaurantData.getById(id);
            if (category == null)
            {
                return NotFound();
            }
            return Ok(category);
        }

        //anda
        // POST api/<controller>
        [Route("api/branch/menu/SocialNetworkRestaurant/{p}")]
        [ResponseType(typeof(SocialNetworkRestaurant))]
        public IHttpActionResult PostsocialNetwork(SocialNetworkRestaurant p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            SocialNetworkRestaurantData.update(p);
            return Ok(p);
        }
        // GET api/<controller>/CategoryPlate
        [Route("api/branch/TypeSocialNetwork")]
        public IList<TypeSocialNetwork> getallTypeSocialNetwork()
        {
            return TypeSocialNetworkData.getAll();
        }
        //anda
        [Route("api/branch/TypeSocial/{id}")]
        public IHttpActionResult DeleteTypeSocial(int id)
        {
            if (TypeSocialNetworkData.getById(id) == null)
            {
                return NotFound();
            }
            TypeSocialNetworkData.Delete(id);
            return Ok();
        }

        [Route("api/branch/TypeSocial/{id}")]
        public IHttpActionResult deletetypeSocial(int id)
        {
            if (TypeSocialNetworkData.getById(id) == null)
            {
                return NotFound();
            }
            TypeSocialNetworkData.Delete(id);
            return Ok(id);
        }

        // POST api/<controller>
        [Route("api/branch/TypeSocialNetwork")]
        [ResponseType(typeof(TypeSocialNetwork))]
        public IHttpActionResult Post(TypeSocialNetwork p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            TypeSocialNetworkData.insert(p);

            return Ok(p);

        }
    
        [Route("api/branch/TypeSocialNetwork")]
        public IHttpActionResult putTypeSocialNetwork(int id, TypeSocialNetwork plate)
        {
            if (id != plate.idTypeSocialNetwork)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del plate es incorrecto.");
            }
            if (TypeSocialNetworkData.getById(plate.idTypeSocialNetwork) == null)
            {
                return NotFound();
            }
            TypeSocialNetworkData.update(plate);
            return Ok(plate);
        }

    }


}
