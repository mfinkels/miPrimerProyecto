<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\ServiceRepository")
 * @ORM\Table(name="service") 
 */
class Service {
    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idService;
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
     * Get id
     *
     * @return integer 
     */
    public function getIdService()
    {
        return $this->idService;
    }

    /**
     * Set name
     *
     * @param string $name
     * @return Service
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