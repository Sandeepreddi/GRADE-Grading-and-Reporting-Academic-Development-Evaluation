import { useState } from 'react';
import { useNavigate,useLocation} from 'react-router-dom';

function FirstTym() {
  const navigate = useNavigate();
  const location = useLocation();
  const email = location.state?.email || '';
  console.log("we got",email);

  const [formData, setFormData] = useState({
    name: '',
    phone_number: '',
    societyName: '',
    societyAddress: '',
    city: '',
    district: '',
    postal: '',
    email: email,
  });

  // Handle input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send data to the backend
      const response = await fetch('http://localhost:8080/admins', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error('Failed to register resident');
      }

      const result = await response.json();
      console.log('Resident registered:', result);
      alert('Registration successful!');
      navigate('/admin/home');
    } catch (error) {
      console.error('Registration error:', error);
      alert('Registration failed. Please try again.');
    }
  };

  return (
    <div className='signup_section'>
      <div className='signup_img'>
        <img
          src="https://assets-news.housing.com/news/wp-content/uploads/2022/03/28143140/Difference-between-flat-and-apartment-446x260.jpg"
          alt="Flat Image"
        />
      </div>

      <div className='signup_form'>
        <h2>Fill these details to continue</h2>

        <form onSubmit={handleSubmit}>
          <input
            name="name"
            placeholder="Name"
            value={formData.name}
            onChange={handleInputChange}
            required
          />
          <input
            name="phone_number"
            type="text"
            placeholder="Phone No"
            value={formData.phone_number}
            onChange={handleInputChange}
            required
          />
          <input
            name="societyName"
            placeholder="Society Name"
            value={formData.societyName}
            onChange={handleInputChange}
            required
          />
          <input
            name="societyAddress"
            placeholder="Society Address"
            value={formData.societyAddress}
            onChange={handleInputChange}
            required
          />
          <input
            name="city"
            placeholder="City"
            value={formData.city}
            onChange={handleInputChange}
            required
          />
          <input
            name="district"
            placeholder="District"
            value={formData.district}
            onChange={handleInputChange}
            required
          />
          <input
            name="postal"
            type="text"
            placeholder="Postal"
            value={formData.postal}
            onChange={handleInputChange}
            required
          />
          

          <button type="submit">Register</button>
        </form>
      </div>
    </div>
  );
}

export default FirstTym;
