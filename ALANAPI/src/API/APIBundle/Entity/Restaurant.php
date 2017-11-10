<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\RestaurantRepository")
 * @ORM\Table(name="restaurant") 
 */
class Restaurant {
    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idRestaurant;
    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $name;
    /**
     * @ORM\Column(type="string", length=200)
     */
    protected $description;
    /**
     * @ORM\OneToMany(targetEntity="SocialNetworkRestaurant", mappedBy="restaurant")
     */
    protected $redesSociales;
    /**
     * @ORM\OneToMany(targetEntity="BranchRestaurant", mappedBy="restaurant")
     */
    protected $branches;
    

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
    public function getIdRestaurant()
    {
        return $this->idRestaurant;
    }

    /**
     * Set name
     *
     * @param string $name
     * @return Restaurant
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
     * Set description
     *
     * @param string $description
     * @return Restaurant
     */
    public function setDescription($description)
    {
        $this->description = $description;
    
        return $this;
    }

    /**
     * Get description
     *
     * @return string 
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Add redesSociales
     *
     * @param \API\APIBundle\Entity\SocialNetworkRestaurant $redesSociales
     * @return Restaurant
     */
    public function addRedesSociale(\API\APIBundle\Entity\SocialNetworkRestaurant $redesSociales)
    {
        $this->redesSociales[] = $redesSociales;
    
        return $this;
    }

    /**
     * Remove redesSociales
     *
     * @param \API\APIBundle\Entity\SocialNetworkRestaurant $redesSociales
     */
    public function removeRedesSociale(\API\APIBundle\Entity\SocialNetworkRestaurant $redesSociales)
    {
        $this->redesSociales->removeElement($redesSociales);
    }

    /**
     * Get redesSociales
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getRedesSociales()
    {
        return $this->redesSociales;
    }

    /**
     * Add branches
     *
     * @param \API\APIBundle\Entity\BranchRestaurant $branches
     * @return Restaurant
     */
    public function addBranche(\API\APIBundle\Entity\BranchRestaurant $branches)
    {
        $this->branches[] = $branches;
    
        return $this;
    }

    /**
     * Remove branches
     *
     * @param \API\APIBundle\Entity\BranchRestaurant $branches
     */
    public function removeBranche(\API\APIBundle\Entity\BranchRestaurant $branches)
    {
        $this->branches->removeElement($branches);
    }

    /**
     * Get branches
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getBranches()
    {
        return $this->branches;
    }
}