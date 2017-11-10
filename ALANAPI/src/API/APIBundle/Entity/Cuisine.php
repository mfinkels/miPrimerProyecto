<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\CuisineRepository")
 * @ORM\Table(name="cuisine") 
 */
class Cuisine {
    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idCuisine;
    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $name;
    

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
     * Get idCuisine
     *
     * @return integer 
     */
    public function getIdCuisine()
    {
        return $this->idCuisine;
    }

    /**
     * Set name
     *
     * @param string $name
     * @return Cuisine
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
}