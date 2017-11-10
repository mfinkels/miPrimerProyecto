<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\SocialNetworkRestaurantRepository")
 * @ORM\Table(name="social_network_restaurant") 
 */
class SocialNetworkRestaurant {
    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idSocialNetworkRestaurant;
    /**
     * @ORM\ManyToOne(targetEntity="Restaurant", inversedBy="redesSociales")
     * @ORM\JoinColumn(name="idRestaurant", referencedColumnName="idRestaurant")
     */
    protected $restaurant;
    /**
     * @ORM\ManyToOne(targetEntity="TypeSocialNetwork", inversedBy="restaurants")
     * @ORM\JoinColumn(name="idTypeSocialNetwork", referencedColumnName="idTypeSocialNetwork")
     */
    protected $typeSocialNetwork;
    
    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $value;
    

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
    public function getIdSocialNetworkRestaurant()
    {
        return $this->idSocialNetworkRestaurant;
    }

    /**
     * Set value
     *
     * @param string $value
     * @return SocialNetworkRestaurant
     */
    public function setValue($value)
    {
        $this->value = $value;
    
        return $this;
    }

    /**
     * Get value
     *
     * @return string 
     */
    public function getValue()
    {
        return $this->value;
    }

    /**
     * Set restaurant
     *
     * @param \API\APIBundle\Entity\Restaurant $restaurant
     * @return SocialNetworkRestaurant
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
     * Set typeSocialNetwork
     *
     * @param \API\APIBundle\Entity\TypeSocialNetwork $typeSocialNetwork
     * @return SocialNetworkRestaurant
     */
    public function setTypeSocialNetwork(\API\APIBundle\Entity\TypeSocialNetwork $typeSocialNetwork = null)
    {
        $this->typeSocialNetwork = $typeSocialNetwork;
    
        return $this;
    }

    /**
     * Get typeSocialNetwork
     *
     * @return \API\APIBundle\Entity\TypeSocialNetwork 
     */
    public function getTypeSocialNetwork()
    {
        return $this->typeSocialNetwork;
    }
}