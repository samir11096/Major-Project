import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import { useOktaAuth } from '@okta/okta-react';
import '@testing-library/jest-dom/extend-expect';

import { LibraryServices } from '../layouts/HomePage/components/LibraryServices';

jest.mock('@okta/okta-react', () => ({
  useOktaAuth: jest.fn(),
}));

describe('LibraryServices', () => {
  beforeEach(() => {
    useOktaAuth.mockReturnValue({ authState: {} });
  });

  test('renders "Sign up" button if user is not authenticated', () => {
    useOktaAuth.mockReturnValue({ authState: { isAuthenticated: false } });

    render(
      <MemoryRouter>
        <LibraryServices />
      </MemoryRouter>
    );

    const signUpButton = screen.getByText('Sign up');
    expect(signUpButton).toBeInTheDocument();
    expect(signUpButton).toHaveAttribute('href', '/login');
  });

});
