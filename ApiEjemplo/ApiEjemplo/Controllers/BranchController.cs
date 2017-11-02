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
        //anda
        [Route("api/branch/Cuisine")]
        public IList<Cuisine> GetCuisine()
        {
            return CuisineData.getAll();
        }
        //seee anda
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
        /*[Route("api/branch/SocialNetworkRestaurant")]
        public IList<SocialNetworkRestaurant> GetlistSocial()
        {
            return SocialNetworkRestaurantData.getAll();
        }
        */

        //anda
        [ResponseType(typeof(CategoryPlate))]
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
        //anda
        [Route("api/branch/DeleteCuisine/{id}")]
        public IHttpActionResult DeleteCuisne(int id)
        {
            if (CuisineData.getById(id) == null)
            {
                return NotFound();
            }
            CuisineData.delete(id);
            return Ok();
        }

        //anda
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


        // GET api/<controller>/CategoryPlate
        [Route("api/branch/CategoryPlate")]
        public IList<CategoryPlate> GetCategory()
        {
            return CategoryPlateData.getAll();
        }

        // GET api/<controller>/CategoryPlate
        [Route("api/branch/TypeSocialNetwork")]
        public IList<TypeSocialNetwork> getallTypeSocialNetwork()
        {
            return TypeSocialNetworkData.getAll();
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
            p.idPlateMenu = PlateMenuData.insert(p);

            return Ok(p);


        }

        // POST api/<controller>
        [Route("api/branch/ServiceBranch")]
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

       

        [Route("api/branch/CusineBranch/{id}")]
        public IHttpActionResult DeleteCusineBranch(int id)
        {
            if (CuisineBranchData.getById(id) == null)
            {
                return NotFound();
            }
            CuisineBranchData.delete(id);
            return Ok(id);
        }


        [Route("api/branch/CuisineBranch/{id}")]
        [ResponseType(typeof(CuisineBranch))]
        public IHttpActionResult GetCuisineBranch(int id)
        {
            CuisineBranch branch = CuisineBranchData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
        }
       

        [Route("api/branch/Cuisine/{id}")]
        [ResponseType(typeof(Cuisine))]
        public IHttpActionResult GetCuisine(int id)
        {
            Cuisine branch = CuisineData.getById(id);
            if (branch == null)
            {
                return NotFound();
            }
            return Ok(branch);
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


        [Route("api/branch/Service/{id}")]
        public IHttpActionResult deleteService(int id)
        {
            if (ServiceData.getById(id) == null)
            {
                return NotFound();
            }
            ServiceData.Delete(id);
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
        [Route("api/branch/Cuisine")]
        public IHttpActionResult putcuisine(int id, Cuisine plate)
        {
            if (id != plate.idCousine)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del plate es incorrecto.");
            }
            if (CuisineData.getById(plate.idCousine) == null)
            {
                return NotFound();
            }
            CuisineData.update(plate);
            return Ok(plate);
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


        // POST api/<controller>
        [Route("api/branch/Service")]
        [ResponseType(typeof(Service))]
        public IHttpActionResult Postservice(Service p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            ServiceData.insert(p);

            return Ok(p);

        }

        // POST api/<controller>
        [Route("api/branch/CuisineBranch")]
        [ResponseType(typeof(CuisineBranch))]
        public IHttpActionResult PostcusineBranch(CuisineBranch p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            CuisineBranchData.insert(p);

            return Ok(p);
        }



        // POST api/<controller>
        [Route("api/branch/Cuisine")]
        [ResponseType(typeof(Cuisine))]
        public IHttpActionResult Postcusine(Cuisine p)
        {
            if (p == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            CuisineData.insert(p);

            return Ok(p);
        }

     

        public IHttpActionResult putcuisine(int id, CuisineBranch plate)
        {
            if (id != plate.idCousine)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del plate es incorrecto.");
            }
            if (CuisineBranchData.getById(plate.idCousine) == null)
            {
                return NotFound();
            }
            CuisineBranchData.update(plate);
            return Ok(plate);
        }





        [Route("api/branch/SocialNetwork")]
        public IList<SocialNetworkRestaurant> GetSocial()
        {
            return SocialNetworkRestaurantData.getAll();
        }




        [Route("api/branch/CusisineBranch")]
        public IList<CuisineBranch> CuisineBranch()
        {
            return CuisineBranchData.getAll();
        }

    }
}
