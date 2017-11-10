<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\RangePriceBranchRepository")
 * @ORM\Table(name="range_price_branch") 
 */
class RangePriceBranch {

    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idRangePriceBranch;

    /**
     * @ORM\ManyToOne(targetEntity="BranchRestaurant", inversedBy="rangoPrecios")
     * @ORM\JoinColumn(name="idBranchRestaurant", referencedColumnName="idBranchRestaurant")
     */
    protected $idBranchRestaurant;

    /**
     * @ORM\Column(type="integer")
     */
    protected $minimum;

    /**
     * @ORM\Column(type="integer")
     */
    protected $maximum;

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
    public function getIdRangePriceBranch()
    {
        return $this->idRangePriceBranch;
    }

    /**
     * Set minimum
     *
     * @param integer $minimum
     * @return RangePriceBranch
     */
    public function setMinimum($minimum)
    {
        $this->minimum = $minimum;
    
        return $this;
    }

    /**
     * Get minimum
     *
     * @return integer 
     */
    public function getMinimum()
    {
        return $this->minimum;
    }

    /**
     * Set maximum
     *
     * @param integer $maximum
     * @return RangePriceBranch
     */
    public function setMaximum($maximum)
    {
        $this->maximum = $maximum;
    
        return $this;
    }

    /**
     * Get maximum
     *
     * @return integer 
     */
    public function getMaximum()
    {
        return $this->maximum;
    }

    /**
     * Set idBranchRestaurant
     *
     * @param \API\APIBundle\Entity\BranchRestaurant $idBranchRestaurant
     * @return RangePriceBranch
     */
    public function setIdBranchRestaurant(\API\APIBundle\Entity\BranchRestaurant $idBranchRestaurant = null)
    {
        $this->idBranchRestaurant = $idBranchRestaurant;
    
        return $this;
    }

    /**
     * Get idBranchRestaurant
     *
     * @return \API\APIBundle\Entity\BranchRestaurant 
     */
    public function getIdBranchRestaurant()
    {
        return $this->idBranchRestaurant;
    }
}