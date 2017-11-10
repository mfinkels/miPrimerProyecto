<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\BranchRestaurantRepository")
 * @ORM\Table(name="branch_restaurant") 
 */
class BranchRestaurant {

    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idBranchRestaurant;

    /**
     * @ORM\ManyToOne(targetEntity="Restaurant", inversedBy="branches")
     * @ORM\JoinColumn(name="idRestaurant", referencedColumnName="idRestaurant")
     */
    protected $restaurant;

    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $name;

    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $latitude;

    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $longitude;

    /**
     * @ORM\Column(type="float", scale=2)
     */
    protected $averageCalification;

    /**
     * @ORM\Column(type="float", scale=2)
     */
    protected $averageFood;

    /**
     * @ORM\Column(type="float", scale=2)
     */
    protected $averageService;

    /**
     * @ORM\Column(type="float", scale=2)
     */
    protected $averageAmbience;
    
    /**
     * @ORM\OneToMany(targetEntity="RangePriceBranch", mappedBy="idBranchRestaurant")
     */
    protected $rangoPrecios;

    /**
     * @ORM\OneToMany(targetEntity="ServiceBranch", mappedBy="branchRestaurant")
     */
//    protected $serviciosBranch;
    
    /**
     * @ORM\OneToMany(targetEntity="PhotoBranch", mappedBy="branchRestaurant")
     */
//    protected $photosBranch;
    /**
     * @ORM\OneToMany(targetEntity="CuisineBranch", mappedBy="branchRestaurant")
     */
//    protected $cuisinesBranch;
    /**
     * @ORM\OneToMany(targetEntity="TypeMenu", mappedBy="branchRestaurant")
     */
//    protected $typesMenu;
    

    /*     * ********************************
     * __construct
     *
     * 
     * ******************************** */

    public function __construct() {
        
    }

    /*     * ********************************
     * __toString()
     *
     * Este método sirve para poder popular los comboboxes en los forms.
     * ******************************* */

    public function __toString() {
        
    }


    /**
     * Get id
     *
     * @return integer 
     */
    public function getIdBranchRestaurant()
    {
        return $this->idBranchRestaurant;
    }
    /**
     * Set name
     *
     * @param string $name
     * @return BranchRestaurant
     */
    public function setName($name)
    {
        $this->name = $name;
    
        return $this;
    }

    /**
     * Get name
     *
     * @return string 
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set latitude
     *
     * @param string $latitude
     * @return BranchRestaurant
     */
    public function setLatitude($latitude)
    {
        $this->latitude = $latitude;
    
        return $this;
    }

    /**
     * Get latitude
     *
     * @return string 
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * Set longitude
     *
     * @param string $longitude
     * @return BranchRestaurant
     */
    public function setLongitude($longitude)
    {
        $this->longitude = $longitude;
    
        return $this;
    }

    /**
     * Get longitude
     *
     * @return string 
     */
    public function getLongitude()
    {
        return $this->longitude;
    }

    /**
     * Set averageCalification
     *
     * @param float $averageCalification
     * @return BranchRestaurant
     */
    public function setAverageCalification($averageCalification)
    {
        $this->averageCalification = $averageCalification;
    
        return $this;
    }

    /**
     * Get averageCalification
     *
     * @return float 
     */
    public function getAverageCalification()
    {
        return $this->averageCalification;
    }

    /**
     * Set averageFood
     *
     * @param float $averageFood
     * @return BranchRestaurant
     */
    public function setAverageFood($averageFood)
    {
        $this->averageFood = $averageFood;
    
        return $this;
    }

    /**
     * Get averageFood
     *
     * @return float 
     */
    public function getAverageFood()
    {
        return $this->averageFood;
    }

    /**
     * Set averageService
     *
     * @param float $averageService
     * @return BranchRestaurant
     */
    public function setAverageService($averageService)
    {
        $this->averageService = $averageService;
    
        return $this;
    }

    /**
     * Get averageService
     *
     * @return float 
     */
    public function getAverageService()
    {
        return $this->averageService;
    }

    /**
     * Set averageAmbience
     *
     * @param float $averageAmbience
     * @return BranchRestaurant
     */
    public function setAverageAmbience($averageAmbience)
    {
        $this->averageAmbience = $averageAmbience;
    
        return $this;
    }

    /**
     * Get averageAmbience
     *
     * @return float 
     */
    public function getAverageAmbience()
    {
        return $this->averageAmbience;
    }

    /**
     * Set restaurant
     *
     * @param \API\APIBundle\Entity\Restaurant $restaurant
     * @return BranchRestaurant
     */
    public function setRestaurant(\API\APIBundle\Entity\Restaurant $restaurant = null)
    {
        $this->restaurant = $restaurant;
    
        return $this;
    }

    /**
     * Get restaurant
     *
     * @return \API\APIBundle\Entity\Restaurant 
     */
    public function getRestaurant()
    {
        return $this->restaurant;
    }

    /**
     * Add rangoPrecios
     *
     * @param \API\APIBundle\Entity\RangePriceBranch $rangoPrecios
     * @return BranchRestaurant
     */
    public function addRangoPrecio(\API\APIBundle\Entity\RangePriceBranch $rangoPrecios)
    {
        $this->rangoPrecios[] = $rangoPrecios;
    
        return $this;
    }

    /**
     * Remove rangoPrecios
     *
     * @param \API\APIBundle\Entity\RangePriceBranch $rangoPrecios
     */
    public function removeRangoPrecio(\API\APIBundle\Entity\RangePriceBranch $rangoPrecios)
    {
        $this->rangoPrecios->removeElement($rangoPrecios);
    }

    /**
     * Get rangoPrecios
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getRangoPrecios()
    {
        return $this->rangoPrecios;
    }

    /**
     * Add serviciosBranch
     *
     * @param \API\APIBundle\Entity\ServiceBranch $serviciosBranch
     * @return BranchRestaurant
     */
    public function addServiciosBranch(\API\APIBundle\Entity\ServiceBranch $serviciosBranch)
    {
        $this->serviciosBranch[] = $serviciosBranch;
    
        return $this;
    }

    /**
     * Remove serviciosBranch
     *
     * @param \API\APIBundle\Entity\ServiceBranch $serviciosBranch
     */
    public function removeServiciosBranch(\API\APIBundle\Entity\ServiceBranch $serviciosBranch)
    {
        $this->serviciosBranch->removeElement($serviciosBranch);
    }

    /**
     * Get serviciosBranch
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getServiciosBranch()
    {
        return $this->serviciosBranch;
    }

    /**
     * Add photosBranch
     *
     * @param \API\APIBundle\Entity\PhotoBranch $photosBranch
     * @return BranchRestaurant
     */
    public function addPhotosBranch(\API\APIBundle\Entity\PhotoBranch $photosBranch)
    {
        $this->photosBranch[] = $photosBranch;
    
        return $this;
    }

    /**
     * Remove photosBranch
     *
     * @param \API\APIBundle\Entity\PhotoBranch $photosBranch
     */
    public function removePhotosBranch(\API\APIBundle\Entity\PhotoBranch $photosBranch)
    {
        $this->photosBranch->removeElement($photosBranch);
    }

    /**
     * Get photosBranch
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getPhotosBranch()
    {
        return $this->photosBranch;
    }

    /**
     * Add cuisinesBranch
     *
     * @param \API\APIBundle\Entity\CuisineBranch $cuisinesBranch
     * @return BranchRestaurant
     */
    public function addCuisinesBranch(\API\APIBundle\Entity\CuisineBranch $cuisinesBranch)
    {
        $this->cuisinesBranch[] = $cuisinesBranch;
    
        return $this;
    }

    /**
     * Remove cuisinesBranch
     *
     * @param \API\APIBundle\Entity\CuisineBranch $cuisinesBranch
     */
    public function removeCuisinesBranch(\API\APIBundle\Entity\CuisineBranch $cuisinesBranch)
    {
        $this->cuisinesBranch->removeElement($cuisinesBranch);
    }

    /**
     * Get cuisinesBranch
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getCuisinesBranch()
    {
        return $this->cuisinesBranch;
    }

    /**
     * Add typesMenu
     *
     * @param \API\APIBundle\Entity\TypeMenu $typesMenu
     * @return BranchRestaurant
     */
    public function addTypesMenu(\API\APIBundle\Entity\TypeMenu $typesMenu)
    {
        $this->typesMenu[] = $typesMenu;
    
        return $this;
    }

    /**
     * Remove typesMenu
     *
     * @param \API\APIBundle\Entity\TypeMenu $typesMenu
     */
    public function removeTypesMenu(\API\APIBundle\Entity\TypeMenu $typesMenu)
    {
        $this->typesMenu->removeElement($typesMenu);
    }

    /**
     * Get typesMenu
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getTypesMenu()
    {
        return $this->typesMenu;
    }
}