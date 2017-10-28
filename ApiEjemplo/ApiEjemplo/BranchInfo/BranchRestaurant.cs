using ApiEjemplo.BranchInfo;
using ApiEjemplo.MenuInfo;
using ApiEjemplo.Models;
using ApiEjemplo.PromotionInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class BranchRestaurant
    {
        public int idBranchRestaurant { get; set; }
        public int idRestaurant { get; set; }
        public Restaurant restaurant { get; set; }
        public int idRangePriceBranch { get; set; }
        public RangePriceBranch RangePrice { get; set; }
        public string name { get; set; }
        public List<PhotoBranch> photo { get; set; }
        public List<Cuisine> cuisine { get; set; }
        public List<TypeFilter> filter { get; set; }
        public List<Service> service { get; set; }
        public List<TimetableBranch> timetable { get; set; }
        public string latitude { get; set; }
        public string longitude { get; set; }
        public List<CalificationBranch> calification { get; set; }
        public double averageFood { get; set; }
        public double averageService { get; set; }
        public double averageAmbience { get; set; }
        public double averageCalification { get; set; }
        public List<Promotion> promotion { get; set; }
    }
}