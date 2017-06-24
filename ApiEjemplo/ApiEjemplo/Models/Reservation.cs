using ApiEjemplo.BranchInfo;
using ApiEjemplo.ReservationInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class Reservation
    {
        public int idReservation { get; set; }
        public int idUser { get; set; }
        public int idBranchRestaurant { get; set; }
        public DateTime date { get; set; }
        public int hour { get; set; }
        public int guest { get; set; }
        public Restaurant restaurant { get; set; }
        public BranchRestaurant branch { get; set; }
    }
}