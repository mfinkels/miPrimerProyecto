<?php

namespace API\APIBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;


/**
 * @ORM\Entity(repositoryClass="API\APIBundle\Entity\TypeSocialNetworkRepository")
 * @ORM\Table(name="type_social_network") 
 */
class TypeSocialNetwork {
    /**
     * @ORM\Column(type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idTypeSocialNetwork;
    /**
     * @ORM\Column(type="string", length=45)
     */
    protected $name;
    /**
     * @ORM\Column(type="string", length=300)
     */
    protected $icon;
    /**
     * @ORM\OneToMany(targetEntity="SocialNetworkRestaurant", mappedBy="typeSocialNetwork")
     */
    protected $restaurants;
    

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
     * Get idTypeSocialNetwork
     *
     * @return integer 
     */
    public function getIdTypeSocialNetwork()
    {
        return $this->idTypeSocialNetwork;
    }

    /**
     * Set name
     *
     * @param string $name
     * @return TypeSocialNetwork
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
     * Set icon
     *
     * @param string $icon
     * @return TypeSocialNetwork
     */
    public function setIcon($icon)
    {
        $this->icon = $icon;
    
        return $this;
    }

    /**
     * Get icon
     *
     * @return string 
     */
    public function getIcon()
    {
        return $this->icon;
    }

    /**
     * Add restaurants
     *
     * @param \API\APIBundle\Entity\SocialNetworkRestaurant $restaurants
     * @return TypeSocialNetwork
     */
    public function addRestaurant(\API\APIBundle\Entity\SocialNetworkRestaurant $restaurants)
    {
        $this->restaurants[] = $restaurants;
    
        return $this;
    }

    /**
     * Remove restaurants
     *
     * @param \API\APIBundle\Entity\SocialNetworkRestaurant $restaurants
     */
    public function removeRestaurant(\API\APIBundle\Entity\SocialNetworkRestaurant $restaurants)
    {
        $this->restaurants->removeElement($restaurants);
    }

    /**
     * Get restaurants
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getRestaurants()
    {
        return $this->restaurants;
    }
}