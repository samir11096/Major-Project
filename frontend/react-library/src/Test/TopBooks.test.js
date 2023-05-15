import React from 'react';
import { render, screen } from '@testing-library/react';
import { ExploreTopBooks } from './../layouts/HomePage/components/ExploreTopBooks';
import { BrowserRouter as Router } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect';


test('ExploreTopBooks renders correctly', () => {
  render(
    <Router>
      <ExploreTopBooks />
    </Router>
  );


  const heading = screen.getByRole('heading', { name: 'Find your next adventure' });
  expect(heading).toBeInTheDocument();

 
  const subheading = screen.getByText('Where would you like to go next?');
  expect(subheading).toBeInTheDocument();


  const exploreLink = screen.getByRole('link', { name: 'Explore top books' });
  expect(exploreLink).toBeInTheDocument();

});

test('ExploreTopBooks link navigates to /search', () => {
  render(
    <Router>
      <ExploreTopBooks />
    </Router>
  );

 
  const exploreLink = screen.getByRole('link', { name: 'Explore top books' });
  expect(exploreLink.getAttribute('href')).toBe('/search');
});