import { createContext, useContext, useEffect, useState, useMemo } from 'react'
import { getCookie, setCookie, removeCookie } from '@/lib/cookies'

type Theme = 'dark' | 'light' | 'system'
type ResolvedTheme = Exclude<Theme, 'system'>

const DEFAULT_THEME = 'system'
const THEME_COOKIE_NAME = 'vite-ui-theme'
const THEME_COLOR_COOKIE_NAME = 'vite-ui-theme-color'
const THEME_COOKIE_MAX_AGE = 60 * 60 * 24 * 365 // 1 year

export type ThemeColor = 'zinc' | 'rose' | 'blue' | 'green' | 'orange'
const DEFAULT_THEME_COLOR: ThemeColor = 'zinc'

type ThemeProviderProps = {
  children: React.ReactNode
  defaultTheme?: Theme
  storageKey?: string
}

type ThemeProviderState = {
  defaultTheme: Theme
  resolvedTheme: ResolvedTheme
  theme: Theme
  setTheme: (theme: Theme) => void
  themeColor: ThemeColor
  setThemeColor: (color: ThemeColor) => void
  resetTheme: () => void
}

const initialState: ThemeProviderState = {
  defaultTheme: DEFAULT_THEME,
  resolvedTheme: 'light',
  theme: DEFAULT_THEME,
  setTheme: () => null,
  themeColor: DEFAULT_THEME_COLOR,
  setThemeColor: () => null,
  resetTheme: () => null,
}

const ThemeContext = createContext<ThemeProviderState>(initialState)

export function ThemeProvider({
  children,
  defaultTheme = DEFAULT_THEME,
  storageKey = THEME_COOKIE_NAME,
  ...props
}: ThemeProviderProps) {
  const [theme, _setTheme] = useState<Theme>(
    () => (getCookie(storageKey) as Theme) || defaultTheme
  )
  const [themeColor, _setThemeColor] = useState<ThemeColor>(
    () => (getCookie(THEME_COLOR_COOKIE_NAME) as ThemeColor) || DEFAULT_THEME_COLOR
  )

  // Optimized: Memoize the resolved theme calculation to prevent unnecessary re-computations
  const resolvedTheme = useMemo((): ResolvedTheme => {
    if (theme === 'system') {
      return window.matchMedia('(prefers-color-scheme: dark)').matches
        ? 'dark'
        : 'light'
    }
    return theme as ResolvedTheme
  }, [theme])

  useEffect(() => {
    const root = window.document.documentElement
    const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)')

    const applyTheme = (currentResolvedTheme: ResolvedTheme, color: ThemeColor) => {
      root.classList.remove('light', 'dark', 'theme-zinc', 'theme-rose', 'theme-blue', 'theme-green', 'theme-orange')
      root.classList.add(currentResolvedTheme)
      root.classList.add(`theme-${color}`)
    }

    const handleChange = () => {
      if (theme === 'system') {
        const systemTheme = mediaQuery.matches ? 'dark' : 'light'
        applyTheme(systemTheme, themeColor)
      }
    }

    applyTheme(resolvedTheme, themeColor)

    mediaQuery.addEventListener('change', handleChange)

    return () => mediaQuery.removeEventListener('change', handleChange)
  }, [theme, resolvedTheme, themeColor])

  const setTheme = (theme: Theme) => {
    setCookie(storageKey, theme, THEME_COOKIE_MAX_AGE)
    _setTheme(theme)
  }

  const setThemeColor = (color: ThemeColor) => {
    setCookie(THEME_COLOR_COOKIE_NAME, color, THEME_COOKIE_MAX_AGE)
    _setThemeColor(color)
  }

  const resetTheme = () => {
    removeCookie(storageKey)
    removeCookie(THEME_COLOR_COOKIE_NAME)
    _setTheme(DEFAULT_THEME)
    _setThemeColor(DEFAULT_THEME_COLOR)
  }

  const contextValue = {
    defaultTheme,
    resolvedTheme,
    resetTheme,
    theme,
    setTheme,
    themeColor,
    setThemeColor,
  }

  return (
    <ThemeContext value={contextValue} {...props}>
      {children}
    </ThemeContext>
  )
}

// eslint-disable-next-line react-refresh/only-export-components
export const useTheme = () => {
  const context = useContext(ThemeContext)

  if (!context) throw new Error('useTheme must be used within a ThemeProvider')

  return context
}
