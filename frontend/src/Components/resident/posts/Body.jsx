import React, { useEffect, useState } from "react";
import './Residentposts.css'

function Body() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
      fetchPosts();
    }, []);

  const fetchPosts = async () => {
    try {
      const response = await fetch("http://localhost:8080/posts/all");
      if (response.ok) {
        const data = await response.json();
        setPosts(data);
      } else {
        console.error("Failed to fetch posts");
      }
    } catch (error) {
      console.error("Error fetching posts:", error);
    }
  };
  return (
    <div className="posts-container">
          <h2 className="post-header">Posts</h2>
          <div className="posts-grid">
            {posts.length === 0 ? (
              <p className="no-posts">No posts available.</p>
            ) : (
              posts.map((post) => (
                <div key={post.id} className="post-item">
                  <img src={`data:image/jpeg;base64,${post.image}`} alt="Post" className="post-image" />
                  <h3>{post.name}</h3>
                  <p>{post.date}</p>
                  <p>{post.description}</p>
                </div>
              ))
            )}
          </div>
        </div>
  );
}

export default Body;