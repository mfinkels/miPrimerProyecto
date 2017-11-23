
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
        // get all the users
        // GET api/<controller>
        public IList<User> Get()
        {
            return UserData.getAll();
        }

        // get one user by Id
        // GET api/<controller>/5
        [ResponseType(typeof(User))]
        public IHttpActionResult GetById(int id)
        {
            User user = UserData.getById(id);
            if (user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }

        // by the email and password you get the userss
        [Route("api/user/LogInUser/{email}/{password}")]
        [HttpGet]
        public IHttpActionResult GetLogInUser(string email, string password)
        {
            User user = new User();
            user = UserData.getUser(email, password);
            if (user == null) {
                return BadRequest("Email o contrasena incorrecta.");
            }
            return Ok(user);
        }

        // return the promotion by Id
        [Route("api/promotion/{idPromotion}")]
        public IHttpActionResult GetPromotion(int idPromotion)
        {
            Promotion p = new Promotion();
            p = PromotionData.getById(idPromotion);
            if (p == null)
            {
                return BadRequest("Promotion doesn't exist.");
            }
            return Ok(p);
        }

        [Route("api/user/promotion/{id}")]
        // return the promotion of user
        public IList<Promotion> GetPromotionUser(int id)
        {
            return PromotionData.getByIdUser(id);
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
        // DELETE: api/Persona/5
        public IHttpActionResult Delete(int id)
        {
            if (UserData.getById(id) == null)
            {
                return NotFound();
            }
            UserData.Delete(id);
            return Ok();
        }
    }
}

/*


Command: "D:\home\site\deployments\tools\deploy.cmd"
Handling .NET Web Application deployment.
MSBuild auto-detection: using msbuild version '14.0' from 'D:\Program Files (x86)\MSBuild\14.0\bin\amd64'.
All packages listed in packages.config are already installed.
Controllers\ServiceBranch.cs(10,28): error CS1002: ; expected [D:\home\site\repository\ApiEjemplo\ApiEjemplo\ApiEjemplo.csproj]
Data\ServiceBranchData.cs(73,73): error CS1001: Identifier expected [D:\home\site\repository\ApiEjemplo\ApiEjemplo\ApiEjemplo.csproj]
Failed exitCode=1, command="D:\Program Files (x86)\MSBuild\14.0\Bin\MSBuild.exe" "D:\home\site\repository\ApiEjemplo\ApiEjemplo\ApiEjemplo.csproj" /nologo /verbosity:m /t:Build /t:pipelinePreDeployCopyAllFilesToOneFolder /p:_PackageTempDir="D:\local\Temp\8d522cc922306a9";AutoParameterizationWebConfigConnectionStrings=false;Configuration=Release;UseSharedCompilation=false /p:SolutionDir="D:\home\site\repository\ApiEjemplo\\"
An error has occurred during web site deployment.
\r\nD:\Program Files (x86)\SiteExtensions\Kudu\67.61027.3099\bin\Scripts\starter.cmd "D:\home\site\deployments\tools\deploy.cmd"





 */