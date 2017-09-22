﻿using ApiEjemplo.Data;
using ApiEjemplo.MenuInfo;
using ApiEjemplo.Models;
using ApiEjemplo.ReservationInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ApiEjemplo.Controllers
{
    public class ReservationController : ApiController
    {
        // GET api/<controller>
        public IList<Reservation> Get()
        {
            return ReservationData.getAll();
        }
        // GET api/<controller>/5
        [ResponseType(typeof(Reservation))]
        public IHttpActionResult Get(int id)
        {
            Reservation res = ReservationData.getById(id);
            if (res == null)
            {
                return NotFound();
            }
            return Ok(res);
        }

        [Route("api/reservation/GetReservationPastByUser/past/{id}")]
        // return list Reservation Past by User
        public IList<Reservation> GetReservationPastByUser(int id)
        {

            return ReservationData.getByIdUser(id, ">");
        }

        [Route("api/reservation/GetReservationUpcomingByUser/upcoming/{id}")]
        // return list Reservation Upcoming by User
        public IList<Reservation> GetReservationUpcomingByUser(int id)
        {

            return ReservationData.getByIdUser(id, "<=");
        }


        [Route("api/reservation/order/{id}")]
        // return the order of Reservation
        public IList<PlateMenu> getOrderOfReservation(int id) {
            return OrderReservationData.getAllByReservation(id);
        }

        [ResponseType(typeof(OrderReservation))]
        public IHttpActionResult Post(OrderReservation order)
        {
            if (order == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            OrderReservationData.insert(order);
            return Ok(order);
        }

        // POST api/<controller>
        [ResponseType(typeof(Reservation))]
        public IHttpActionResult Post(Reservation res)
        {
            if (res == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            ReservationData.insert(res);
            return Ok("");
        }

        // POST api/<controller>
        [Route("reservation/order")]
        [ResponseType(typeof(OrderReservation))]
        public IHttpActionResult PostOrder(OrderReservation order)
        {
            if (order == null)//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            OrderReservationData.insert(order);
            return Ok(order);
        }

        // PUT api/<controller>/5
        public IHttpActionResult Put(int id, Reservation res)
        {
            if (id != res.idReservation)//Nos tiene que llegar el objeto correctamente
            {
                return BadRequest("El id del user es incorrecto.");
            }
            if (ReservationData.getById(id) == null)
            {
                return NotFound();
            }
            ReservationData.update(res);
            return Ok(res);
        }

        // DELETE: api/<controller>/5
        public IHttpActionResult Delete(int id)
        {
            if (ReservationData.getById(id) == null)
            {
                return NotFound();
            }
            ReservationData.delete(id);
            return Ok();
        }
    }
}
