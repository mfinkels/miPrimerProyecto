using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.ReservationInfo
{
    public class OrderReservation
    {
        public int idOrderReservation { get; set; }
        public int idReservation { get; set; }
        public int idPlateMenu { get; set; }
        public int idUser { get; set; }
        public PlateMenu plate { get; set; }
    }
}